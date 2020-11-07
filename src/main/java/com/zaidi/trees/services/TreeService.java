package com.zaidi.trees.services;

import java.util.List;
import com.zaidi.trees.entities.*;
import com.zaidi.trees.repositories.TreeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TreeService implements ITreeService {

    @Autowired
    private TreeRepository repository;

    @Override
    public List<Tree> getAllTrees() {
        return (List<Tree>) repository.findAll();
    }

    @Override
    public Tree createTree(Tree tree) {
        return repository.save(tree);
    }
}
