package com.feedback.feedback;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.feedback.feedback.config.JwtTokenProvider;
import com.feedback.feedback.controller.TenantController;
import com.feedback.feedback.dto.FeedbackCreateDto;
import com.feedback.feedback.dto.FeedbackUpdateDto;
import com.feedback.feedback.facade.FeedbackFacade;
import com.feedback.feedback.facade.impl.FeedbackFacadeImpl;
import com.feedback.feedback.model.User;
import io.jsonwebtoken.SignatureException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class FeedbackRestTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private FeedbackFacade facade;

    private static final String GOOD_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0IiwidXNlcm5hbWUiOiJ0ZXN0Iiwicm9sZSI6InRlc3QtdXNlciJ9.9KxwqBU7p_ipsLCUntuOwGlIy8082IODPjEe6YdfRv8";
    private static final String BAD_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0IiwidXNlcm5hbWUiOiJ0ZXN0Iiwicm9sZSI6InRlc3QtdXNlciJ9.9KxwqBU7p_ipsLCUntuOwGlIy8082IODPj";
    private static final String ID = "123";
    @Test
    void addSuccess() throws Exception {

        FeedbackCreateDto dto = new FeedbackCreateDto("Test","Great test!", "test");
        when(facade.create(dto,GOOD_TOKEN)).thenReturn(ID);

        String json = new ObjectMapper().writeValueAsString(dto);
        MvcResult result = mvc.perform(
                post("/tenant/create")
                .header("Token", GOOD_TOKEN)
                .contentType(APPLICATION_JSON)
                .content(json))
                .andReturn();
        assert(result.getResponse().getStatus() == 200);
        assert(result.getResponse().getContentAsString().equals(ID));
    }

    @Test
    void addFail() throws Exception {

        FeedbackCreateDto dto = new FeedbackCreateDto("Test","Great test!", "test");
        when(facade.create(dto,BAD_TOKEN)).thenThrow( new SignatureException("Token not valid"));

        String json = new ObjectMapper().writeValueAsString(dto);
        MvcResult result = mvc.perform(
                post("/tenant/create")
                        .header("Token", BAD_TOKEN)
                        .contentType(APPLICATION_JSON)
                        .content(json))
                .andReturn();
        assert(result.getResponse().getStatus() == 403);
        assert(result.getResponse().getContentAsString().equals("Token not valid!"));
    }

    @Test
    void deleteSuccess() throws Exception{
        when(facade.delete(ID,GOOD_TOKEN)).thenReturn(true);
        MvcResult result = mvc.perform(
                delete("/tenant/delete/"+ID)
                        .header("Token", GOOD_TOKEN))
                .andReturn();
        assert(result.getResponse().getStatus() == 200);
    }

    @Test
    void deleteFail() throws Exception{
        when(facade.delete(ID,GOOD_TOKEN)).thenReturn(false);
        MvcResult result1 = mvc.perform(
                delete("/tenant/delete/"+ID)
                        .header("Token", GOOD_TOKEN))
                .andReturn();
        assert(result1.getResponse().getStatus() == 404);

        when(facade.delete(ID,BAD_TOKEN)).thenThrow(new SignatureException("Token not valid"));
        MvcResult result2 = mvc.perform(
                delete("/tenant/delete/"+ID)
                        .header("Token", BAD_TOKEN))
                .andReturn();
        assert(result2.getResponse().getStatus() == 403);
    }

    @Test
    void updateContentSuccess() throws  Exception{
        FeedbackUpdateDto dto = new FeedbackUpdateDto(ID,"test","test");
        String json = new ObjectMapper().writeValueAsString(dto);
        when(facade.updateFeedback(dto,GOOD_TOKEN)).thenReturn(true);
        MvcResult result = mvc.perform(
                put("/tenant/update")
                        .header("Token", GOOD_TOKEN)
                        .contentType(APPLICATION_JSON)
                        .content(json))
                .andReturn();
        assert(result.getResponse().getStatus() == 200);
    }

    @Test
    void updateContentFail() throws  Exception{
        FeedbackUpdateDto dto = new FeedbackUpdateDto(ID,"test","test");
        String json = new ObjectMapper().writeValueAsString(dto);

        when(facade.updateFeedback(dto,GOOD_TOKEN)).thenReturn(false);
        MvcResult result1 = mvc.perform(
                put("/tenant/update")
                        .header("Token", GOOD_TOKEN)
                        .contentType(APPLICATION_JSON)
                        .content(json))
                .andReturn();
        assert(result1.getResponse().getStatus() == 404);

        when(facade.updateFeedback(dto,BAD_TOKEN)).thenThrow(new SignatureException("Token not valid"));
        MvcResult result2 = mvc.perform(
                put("/tenant/update")
                        .header("Token", BAD_TOKEN)
                        .contentType(APPLICATION_JSON)
                        .content(json))
                .andReturn();
        assert(result2.getResponse().getStatus() == 403);
    }

    @Test
    void voteSuccess() throws Exception{
        when(facade.updateVote(ID,1L,GOOD_TOKEN)).thenReturn(true);
        MvcResult result = mvc.perform(
                put("/tenant/vote/"+ID)
                        .header("Token", GOOD_TOKEN))
                .andReturn();
        assert(result.getResponse().getStatus() == 200);
    }


    @Test
    void voteFail() throws Exception{
        when(facade.updateVote(ID,1L,GOOD_TOKEN)).thenReturn(false);
        MvcResult result1 = mvc.perform(
                put("/tenant/vote/"+ID)
                        .header("Token", GOOD_TOKEN))
                .andReturn();
        assert(result1.getResponse().getStatus() == 404);

        when(facade.updateVote(ID,1L,BAD_TOKEN)).thenThrow(new SignatureException("Token not valid"));
        MvcResult result2 = mvc.perform(
                put("/tenant/vote/"+ID)
                        .header("Token", BAD_TOKEN))
                .andReturn();
        assert(result2.getResponse().getStatus() == 403);
    }

    @Test
    void unvoteSuccess() throws Exception{
        when(facade.updateVote(ID,-1L,GOOD_TOKEN)).thenReturn(true);
        MvcResult result = mvc.perform(
                put("/tenant/unvote/"+ID)
                        .header("Token", GOOD_TOKEN))
                .andReturn();
        assert(result.getResponse().getStatus() == 200);
    }


    @Test
    void unvoteFail() throws Exception{
        when(facade.updateVote(ID,-1L,GOOD_TOKEN)).thenReturn(false);
        MvcResult result1 = mvc.perform(
                put("/tenant/unvote/"+ID)
                        .header("Token", GOOD_TOKEN))
                .andReturn();
        assert(result1.getResponse().getStatus() == 404);

        when(facade.updateVote(ID,-1L,BAD_TOKEN)).thenThrow(new SignatureException("Token not valid"));
        MvcResult result2 = mvc.perform(
                put("/tenant/unvote/"+ID)
                        .header("Token", BAD_TOKEN))
                .andReturn();
        assert(result2.getResponse().getStatus() == 403);
    }
}
