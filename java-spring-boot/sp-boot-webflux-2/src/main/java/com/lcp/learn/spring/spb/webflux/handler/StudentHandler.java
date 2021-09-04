package com.lcp.learn.spring.spb.webflux.handler;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.TEXT_PLAIN;
import static org.springframework.web.reactive.function.BodyInserters.fromValue;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

import com.lcp.learn.spring.spb.webflux.beans.Student;
import org.springframework.beans.BeanUtils;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2021/1/7-14:04
 */
public class StudentHandler {

  public Mono<ServerResponse> selectStudent(ServerRequest request) {

    Student studentBody = new Student();

    request
        .bodyToMono(Student.class)
        .subscribe(student ->
            BeanUtils.copyProperties(student, studentBody));

    return ok()
        .contentType(APPLICATION_JSON)
        .body(fromValue(studentBody));
  }

  public Mono<ServerResponse> insertStudent(ServerRequest request) {

    return ok()
        .contentType(TEXT_PLAIN)
        .body(fromValue("success"));

  }

}
