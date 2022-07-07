package com.school.common.entity;

import java.util.Optional;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.school.common.dto.StudentAndTopicDto;

@Entity
@Table(name = "student_and_topic")
public class StudentAndTopic extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	@OneToOne
	@JoinColumn(name = "student_id")
	private User student;
	@OneToOne
	@JoinColumn(name = "topic_id")
	private Topic topic;
	private String content;
	
	
	public StudentAndTopic() {
		
	}

	public StudentAndTopic(Long id) {
		this.setId(id);
	}

	public StudentAndTopic(StudentAndTopicDto dto) {
		Optional<StudentAndTopicDto> opDto = Optional.ofNullable(dto);
		if (opDto.isPresent()) {
			this.setId(dto.getId());
			this.setStudent(User.convertToUser(dto.getStudent()));
			this.setTopic(new Topic(dto.getTopic()));
			this.setContent(dto.getContent());
		}
	}

	public StudentAndTopic(Long id, User student, Topic topic, String content) {
		this.setId(id);
		this.setStudent(student);
		this.setTopic(topic);
		this.setContent(content);
	}
	
	public User getStudent() {
		return student;
	}

	public void setStudent(User student) {
		this.student = student;
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
		StudentAndTopic other = (StudentAndTopic) obj;
		if (this.getId() == null) {
			if (other.getId() != null)
				return false;
		} else if (!this.getId().equals(other.getId()))
			return false;
		return true;
	}

}
