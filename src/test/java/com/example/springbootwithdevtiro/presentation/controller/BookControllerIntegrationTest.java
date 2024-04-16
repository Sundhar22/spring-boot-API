package com.example.springbootwithdevtiro.presentation.controller;

import com.example.springbootwithdevtiro.Service.BookService;
import com.example.springbootwithdevtiro.TestUtils.TestUtil;
import com.example.springbootwithdevtiro.persistence.entity.BookEntity;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class BookControllerIntegrationTest {

    private final MockMvc mockMvc;
    private final ObjectMapper mapper;
    private final BookService service;

    @Autowired
    public BookControllerIntegrationTest(MockMvc mockMvc, ObjectMapper mapper, BookService service) {
        this.mockMvc = mockMvc;
        this.mapper = mapper;
        this.service = service;
    }

    @Test
    public void testThatPutOpInBookPath() throws Exception {
//Update - uncomment the commented
//        var temp = TestUtil.bookDaoObj ( null );
//        service.createBook ( temp.getIsbn ( ), temp );
        var book = TestUtil.bookDToObj ( null );
        var bookStr = mapper.writeValueAsString ( book );

        mockMvc.perform (
                       MockMvcRequestBuilders.put ( "/books/" +
//                                                     temp.getIsbn ( )
                                                             book.getIsbn ( )
                                             )
                                             .contentType ( MediaType.APPLICATION_JSON )
                                             .content ( bookStr )
               )
//               .andExpect (
//                MockMvcResultMatchers.status ( ).isOk ( )
//        )
               .andExpect (
                       MockMvcResultMatchers.status ( ).isCreated ( )
               )
        ;
    }

    @Test
    public void testingThatSameValueGettingInResponse() throws Exception {
        var book = TestUtil.bookDToObj ( null );
        var bookStr = mapper.writeValueAsString ( book );
        mockMvc.perform (
                MockMvcRequestBuilders.put ( "/books/" + book.getIsbn ( ) )
                                      .contentType ( MediaType.APPLICATION_JSON )
                                      .content ( bookStr )
        ).andExpect (
                MockMvcResultMatchers.status ( ).isCreated ( )
        ).andExpect (
                MockMvcResultMatchers.jsonPath ( "$.isbn" ).value ( book.getIsbn ( ) )
        );
    }

    @Test
    public void testingThatFindAllFunWithResponse200() throws Exception {
        service.createBook ( "1332-2344-34", TestUtil.bookDaoObj ( null ) );
        mockMvc.perform (
                MockMvcRequestBuilders.get ( "/books" ).contentType ( MediaType.APPLICATION_JSON )
        ).andExpect (
                MockMvcResultMatchers.status ( ).isOk ( )
        );
    }

    @Test
    public void testingThatFindOneBookWithAllResponse() throws Exception {
        BookEntity bookEntity = TestUtil.bookDaoObj ( null );
        bookEntity.setIsbn ( "1332-2344-34" );

        service.createBook ( bookEntity.getIsbn ( ), bookEntity );
        mockMvc.perform (
                       MockMvcRequestBuilders.get ( "/books/" + bookEntity.getIsbn ( ) )
                                             .contentType ( MediaType.APPLICATION_JSON )
               ).andExpect (
                       MockMvcResultMatchers.status ( ).isOk ( )
               )
               .andExpect (
                       MockMvcResultMatchers.jsonPath ( "$.isbn" ).value ( "1332-2344-34" )
               )
        ;
    }
}
