package com.felipe.todo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private boolean checked;

    private String description;

    @ApiModelProperty(required = false, example = "2018-07-25 10:30")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "YYYY-MM-dd HH:mm", timezone = "GMT")
    private Date dueDate;

    @JsonIgnore
    @ManyToOne
    private TaskList taskList;

    @JsonIgnore
    @LastModifiedDate
    private LocalDateTime updateDate;

    @JsonIgnore
    @CreatedDate
    private LocalDateTime creationDate;

}
