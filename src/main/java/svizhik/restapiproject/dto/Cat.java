package svizhik.restapiproject.dto;
import lombok.*;
//import org.springframework.data.annotation.Id;


@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cat {

    private Integer id;
    private String name;
    private int age;
    private int weight;

}

