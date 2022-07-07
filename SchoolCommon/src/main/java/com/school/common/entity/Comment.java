package com.school.common.entity;

import java.util.Optional;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.school.common.dto.AcademicTranscriptStudentPointDto;
import com.school.common.dto.CommentDto;
import com.school.common.dto.TopicDto;
import com.school.common.dto.UserDto;

@Entity
@Table(name="comment")
public class Comment extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;

	@OneToOne
	@JoinColumn(name = "topic_id")
	private Topic topic;
	
	private String content;
	
	public Comment(User user, Topic topic, String content) {
		this.user = user;
		this.topic = topic;
		this.content = content;
	}
	
	public Comment() {
	}
	
	public Comment(Long id) {
		this.setId(id);
	}

	public Comment(CommentDto dto) {
		if (dto != null) {
			this.setId(dto.getId());
			this.user = User.convertToUser(dto.getUser());
			this.topic = new Topic(dto.getTopic());
			this.content = dto.getContent();
		}
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (this.getId() == null ? 0 : this.getId().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (this.getClass() != obj.getClass())
			return false;
		Comment other = (Comment) obj;
		if (this.getId() == null) {
			if (other.getId() != null)
				return false;
		} else if (!this.getId().equals(other.getId()))
			return false;
		return true;
	}
	
	@Transient
	public static Comment convertToAcademicTranscript(CommentDto userDto) {
		Optional<CommentDto> op = Optional.ofNullable(userDto);
		if (op.isPresent()) {
			CommentDto academicDto = op.get();
			Comment academic = new Comment();
			academic.setId(academicDto.getId());
			academic.setUser(User.convertToUser(academicDto.getUser()));
			academic.setTopic(new Topic(academicDto.getTopic()));
			academic.setContent(academicDto.getContent());
			return academic;
		}
		return null;
	}
}
