package com.zaidi.trees.services;

import org.springframework.stereotype.Repository;
import java.util.List;

import com.zaidi.trees.entities.*;

@Repository
public interface ITreeService {
    List<Tree> getAllTrees();

    Tree createTree(Tree tree);
}
