package com.example.demo;

import java.io.PrintStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.Banner.Mode;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.core.env.Environment;

import com.example.demo.domain.Student;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.StudentRepository;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private MemberRepository memberRepository;	

	public static void main(String[] args) {
		new SpringApplicationBuilder().banner(new Banner() {
			@Override
			public void printBanner(Environment environment, Class<?> sourceClass, PrintStream out) {
				out.println("#############");
				out.println("Custom Banner");
				out.println("#############");
			}
		})
		.bannerMode(Mode.LOG)
		.logStartupInfo(true) // 시동시 로깅 여부 기본값 true
		.sources(DemoApplication.class)
		.run(args);
	}

	@Override
	public void run(String... args) throws Exception {
		studentRepository.deleteAll();

		Student student1 = new Student("Na Yun Su");
		studentRepository.save(student1);
		
		memberRepository.deleteAll();
	}
}
