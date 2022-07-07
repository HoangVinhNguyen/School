package com.school.common.dto;

import java.time.LocalDateTime;

import com.school.common.entity.StudentAndTopic;

public class StudentAndTopicDto extends BaseDto {

	private static final long serialVersionUID = 1L;

	private UserDto student;
	private TopicDto topic;
	private String content;
	private String createdBy;
	private String modifiedBy;
	private LocalDateTime createdDate;
	private LocalDateTime modifiedDate;
	
	public StudentAndTopicDto() {
		
	}

	public StudentAndTopicDto(Long id) {
		this.setId(id);
	}

	public StudentAndTopicDto(StudentAndTopic entity) {
		if (entity != null) {
			this.setId(entity.getId());
			this.setStudent(new UserDto(entity.getStudent()));
			this.setTopic(new TopicDto(entity.getTopic()));
			this.setContent(entity.getContent());
		}
	}

	public UserDto getStudent() {
		return student;
	}

	public void setStudent(UserDto student) {
		this.student = student;
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

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public LocalDateTime getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(LocalDateTime modifiedDate) {
		this.modifiedDate = modifiedDate;
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
		StudentAndTopicDto other = (StudentAndTopicDto) obj;
		if (this.getId() == null) {
			if (other.getId() != null)
				return false;
		} else if (!this.getId().equals(other.getId()))
			return false;
		return true;
	}
	
}
