package com.pioslomiany.DDSProject.toDoList.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="todo_list")
@Getter @Setter
public class ToDoItem {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="category")
	private String category;
	
	@DateTimeFormat(iso = ISO.DATE, fallbackPatterns = { "M/d/yy", "dd.MM.yyyy" })
	@Column(name="dead_line_date")
	private LocalDate deadLineDate;

	@NotEmpty(message="Pole nie może być puste")
	@Column(name="description")
	private String description;
	
	@Column(name="status")
	private String isDone;
	
	public ToDoItem() {
		this.isDone = "W trakcie";
	}

}
