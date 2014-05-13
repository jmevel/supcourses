package com.supinfo.supcourses.dao;

import com.supinfo.supcourses.entities.Question;
import com.supinfo.supcourses.entities.Quizz;
import java.util.ArrayList;
import java.util.Collection;
import javax.ejb.Local;

@Local
public interface QuestionDao {
    Question createQuestion(Question question);
    ArrayList<Question> createQuestions(ArrayList<Question> questions);
}
