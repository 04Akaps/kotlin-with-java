package model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class MoviesWithComments {
    private Object id;
    private String title;
    private List<Comments> comments;
}
