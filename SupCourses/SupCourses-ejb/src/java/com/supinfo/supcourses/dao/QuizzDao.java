package com.supinfo.supcourses.dao;

import com.supinfo.supcourses.entities.Answer;
import com.supinfo.supcourses.entities.Course;
import com.supinfo.supcourses.entities.Quizz;
import java.util.ArrayList;
import javax.ejb.Local;

@Local
public interface QuizzDao {
    Quizz createQuizz(Quizz quizz);
    Quizz getQuizzByCourseId(Long courseId);
    int correctQuizz(long courseId, ArrayList<Answer> answers);
}
