package com.example.demo;

import java.io.PrintStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.Banner.Mode;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.core.env.Environment;

import com.example.demo.repository.MemberRepository;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	@Autowired
	private MemberRepository memberRepository;	

	public static void main(String[] args) {
		new SpringApplicationBuilder().banner(new Banner() {
			@Override
			public void printBanner(Environment environment, Class<?> sourceClass, PrintStream out) {
				out.println("Custom Banner");
			}
		})
		.bannerMode(Mode.LOG)
		.logStartupInfo(true) // 시동시 로깅 여부 기본값 true
		.sources(DemoApplication.class)
		.run(args);
	}

	@Override
	public void run(String... args) throws Exception {
		memberRepository.deleteAll();
	}
}
