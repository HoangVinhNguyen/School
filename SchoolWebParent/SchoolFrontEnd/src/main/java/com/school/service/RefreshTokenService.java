package com.school.service;

import java.util.Optional;

import com.school.common.entity.RefreshToken;
import com.school.common.entity.User;

public interface RefreshTokenService {

	public Optional<RefreshToken> findByToken(String token);
	public RefreshToken createRefreshToken(Long userId);
	public void updateRefreshToken(String reToken, Long userId);
	public RefreshToken verifyExpiration(RefreshToken token);
	public int deleteByUserId(Long userId);
}
