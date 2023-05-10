package com.example.email01.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final List<Student> students;

    @Autowired
    private JavaMailSender emailSender;

    public StudentService() {
        students = new ArrayList<>();
        students.add(new Student(1,"Giulia", "Contarino", "maxpower88999@gmail.com"));
        students.add(new Student(2,"John", "Doe", "john.doe@example.com"));
        students.add(new Student(3,"Jane", "Doe", "jane.doe@example.com"));
        students.add(new Student(4,"Bob", "Smith", "bob.smith@example.com"));
    }

    public void sendEmailToStudent(long contactId, String subject, String text) {
        Optional<Student> optionalStudent = students.stream().filter(s -> s.getId() == contactId).findFirst();
        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(student.getEmail());
            message.setSubject(subject);
            message.setText(text);
            message.setReplyTo("test@example.com");
            message.setFrom("maxpower88999@gmail.com");
        } else {
            throw new IllegalArgumentException("Student not found with contact ID " + contactId);
        }
    }
}
