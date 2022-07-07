package com.school.common.dto;

import com.school.common.entity.Comment;

public class CommentDto extends BaseDto {

	private static final long serialVersionUID = 1L;

	private UserDto user;
	private TopicDto topic;
	private String content;

	public CommentDto(UserDto user, TopicDto topic, String content) {
		this.user = user;
		this.topic = topic;
		this.content = content;
	}
	
	public CommentDto() {
	}
	
	public CommentDto(Long id) {
		this.setId(id);
	}

	public CommentDto(Comment entity) {
		if (entity != null) {
			this.setId(entity.getId());
			this.user = new UserDto(entity.getUser());
			this.topic = new TopicDto(entity.getTopic());
			this.content = entity.getContent();
		}
	}

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}

	public TopicDto getTopic() {
		return topic;
	}

	public void setTopic(TopicDto topic) {
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
		result = prime * result + ((this.getId() == null) ? 0 : this.getId().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CommentDto other = (CommentDto) obj;
		if (this.getId() == null) {
			if (other.getId() != null)
				return false;
		} else if (!this.getId().equals(other.getId()))
			return false;
		return true;
	}

}
