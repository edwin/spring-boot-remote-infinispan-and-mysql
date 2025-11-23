package com.edw.bean;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Indexed;

import java.io.Serializable;

/**
 * <pre>
 *  com.edw.bean.Employee
 * </pre>
 *
 * @author Muhammad Edwin < edwin at redhat dot com >
 * 23 Nov 2025 15:02
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Indexed

@Entity
@Table(name = "t_employee")
public class Employee implements Serializable {

    @Id
    public Long id;

    @Column(name = "gender")
    public String gender;

    @Column(name = "firstname")
    public String firstname;

    @Column(name = "lastname")
    public String lastname;
}
