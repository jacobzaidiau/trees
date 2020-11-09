package com.zaidi.trees.controllers;

import java.util.List;
import com.zaidi.trees.entities.*;
import com.zaidi.trees.services.TreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/")
public class TreeApiController {
    @Autowired
    TreeService treeService;

    @GetMapping("")
    public List<Tree> getAllTrees() {
        return treeService.getAllTrees();
    }

    @PostMapping("")
    public Tree createTree(@RequestBody Tree tree) {
        return treeService.createTree(tree);
    }
}
