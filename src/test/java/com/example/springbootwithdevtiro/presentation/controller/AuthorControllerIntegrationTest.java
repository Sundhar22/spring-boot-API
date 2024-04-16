package com.example.springbootwithdevtiro.presentation.controller;

import com.example.springbootwithdevtiro.Service.AuthorService;
import com.example.springbootwithdevtiro.TestUtils.TestUtil;
import com.example.springbootwithdevtiro.persistence.entity.AuthorEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static com.example.springbootwithdevtiro.TestUtils.TestUtil.authorObj;
import static com.example.springbootwithdevtiro.TestUtils.TestUtil.authorObj3;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class AuthorControllerIntegrationTest {

    private final MockMvc mockMvc;
    private final ObjectMapper mapper;

    private final AuthorService service;

    @Autowired
    public AuthorControllerIntegrationTest(MockMvc mockMvc, ObjectMapper mapper, AuthorService service) {
        this.mockMvc = mockMvc;
        this.mapper = mapper;
        this.service = service;
    }

    @Test
    public void findingStatusThatSuccess() throws Exception {
        AuthorEntity author = TestUtil.authorObj ( );
        String objToString = mapper.writeValueAsString ( author );
        mockMvc.perform (
                MockMvcRequestBuilders.post ( "/authors" )
                                      .contentType ( MediaType.APPLICATION_JSON )
                                      .content ( objToString )
        ).andExpect (
                MockMvcResultMatchers.status ( ).isCreated ( )
        );
    }

    @Test
    public void findingAuthorObjThatSuccess() throws Exception {
        AuthorEntity author = authorObj3 ( );
        author.setId ( null );
        String objToString = mapper.writeValueAsString ( author );
        mockMvc.perform (
                MockMvcRequestBuilders.post ( "/authors" )
                                      .contentType ( MediaType.APPLICATION_JSON )
                                      .content ( objToString )
        ).andExpect (
                MockMvcResultMatchers.jsonPath ( "$.id" ).isNumber ( )
        ).andExpect (
                MockMvcResultMatchers.jsonPath ( "$.name" ).value ( author.getName ( ) )
        ).andExpect (
                MockMvcResultMatchers.jsonPath ( "$.age" ).value ( author.getAge ( ) )
        );
    }

    @Test
    public void testingThatStatusAs200ForSuccessAndCheckingExpectedVal() throws Exception {

        AuthorEntity author = authorObj3 ( );
        service.saveAuthor ( author );
        ResultActions resultActions = mockMvc.perform (

                                                     MockMvcRequestBuilders.get ( "/authors" ).contentType ( MediaType.APPLICATION_JSON )
                                             )
                                             .andExpect ( MockMvcResultMatchers.status ( ).isOk ( ) )
                                             .andExpect (
                                                     MockMvcResultMatchers.jsonPath ( "$[0].id" ).isNumber ( )
                                             );
        System.out.println ( resultActions );
    }

    @Test
    public void testingThatFindOneWithResponse200AndCorrectOne() throws Exception {
        var author = authorObj ( );
        service.saveAuthor ( author );
        mockMvc.perform (
                       MockMvcRequestBuilders.get ( "/authors/" + author.getId ( ) )
                                             .contentType ( MediaType.APPLICATION_JSON )
               ).andExpect (
                       MockMvcResultMatchers.status ( ).isOk ( )
               )
               .andExpect (
                       MockMvcResultMatchers.jsonPath ( "$.id" ).isNumber ( )
               ).andExpect (
                       MockMvcResultMatchers.jsonPath ( "$.age" ).value ( author.getAge ( ) )
               )
        ;
    }

    @Test
    public void testingThatUpdatingAuthor() throws Exception {

        var author = TestUtil.authorObj ( );
        service.saveAuthor ( author );
        author.setId ( 2L );
        author.setName ( "Heloona ori" );
        author.setAge ( 35 );
        mockMvc.perform (
                MockMvcRequestBuilders.put ( "/authors/" + 1 ).contentType ( MediaType.APPLICATION_JSON ).content (
                        mapper.writeValueAsString (
                                author
                        )
                )
        ).andExpect (
                MockMvcResultMatchers.status ( ).isOk ( )
        ).andExpect (
                MockMvcResultMatchers.jsonPath ( "$.id" ).value ( 1 )
        ).andExpect (
                MockMvcResultMatchers.jsonPath ( "$.name" ).value ( "Heloona ori" )
        );
    }
}
