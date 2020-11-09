package com.zaidi.trees.controllers;

import java.util.ArrayList;
import java.util.List;
import com.zaidi.trees.entities.Tree;
import com.zaidi.trees.services.TreeService;
import static org.mockito.BDDMockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.CoreMatchers.is;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import static org.mockito.ArgumentMatchers.any;

@WebMvcTest(controllers = TreeApiController.class)
@ActiveProfiles("test")
public class TreeApiControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TreeService treeService;

    private List<Tree> treeList;

    @BeforeEach
    void setUp() {
        this.treeList = new ArrayList<>();
        this.treeList.add(new Tree(1, "Kauri", 100));
        this.treeList.add(new Tree(2, "Rimu", 200));
        this.treeList.add(new Tree(3, "Kowhai", 150));
    }

    @Test
    void shouldGetAllTrees() throws Exception {
        given(treeService.getAllTrees()).willReturn(treeList);

        this.mockMvc.perform(get("/")).andExpect(status().isOk()).andExpect(jsonPath("$.size()", is(treeList.size())));
    }

    @Test
    void shouldCreateTree() throws Exception {
        given(treeService.createTree(any(Tree.class))).willAnswer((invocation) -> invocation.getArgument(0));

        Tree tree = new Tree(4, "Kowhai", 101);

        this.mockMvc
                .perform(post("/").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(tree)))
                .andExpect(status().isOk()).andExpect(jsonPath("$.name", is(tree.getName())))
                .andExpect(jsonPath("$.height", is((int) tree.getHeight() + 1)));
    }
}
