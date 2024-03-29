package com.school.service.impl;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.common.entity.RefreshToken;
import com.school.exception.TokenRefreshException;
import com.school.repository.RefreshTokenRepository;
import com.school.repository.UserRepository;
import com.school.security.jwt.JwtUtils;

@Service
public class RefreshTokenService {

	private Long refreshTokenDurationMs = 120000L;

	@Autowired
	private RefreshTokenRepository refreshTokenRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	JwtUtils jwtUtils;

	public Optional<RefreshToken> findByToken(String token) {
		return refreshTokenRepository.findByToken(token);
	}

	public RefreshToken createRefreshToken(Long userId) {
		RefreshToken refreshToken = new RefreshToken();

		refreshToken.setUser(userRepository.findById(userId).get());
		refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
		refreshToken.setToken(UUID.randomUUID().toString());

		refreshToken = refreshTokenRepository.save(refreshToken);
		return refreshToken;
	}
	
	public void updateRefreshToken(String reToken, Long userId) {
		Optional<RefreshToken> opRefreshToken = refreshTokenRepository.findByToken(reToken);
		if (opRefreshToken.isPresent()) {
			RefreshToken refreshToken = opRefreshToken.get();
			if (refreshToken.getUser().getId() == userId) {
				refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
				refreshToken = refreshTokenRepository.save(refreshToken);
			}
		}
		
	}

	public RefreshToken verifyExpiration(RefreshToken token) {
		if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
			refreshTokenRepository.delete(token);
			throw new TokenRefreshException(token.getToken(), "Refresh token was expired. Please re-signin");
		}

		return token;
	}

	@Transactional
	public int deleteByUserId(Long userId) {
		return refreshTokenRepository.deleteByUser(userRepository.findById(userId).get());
	}
}
