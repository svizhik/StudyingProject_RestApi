package svizhik.restapiproject.dto;
import lombok.*;


@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cat {
    private String name;
    private int age;
    private int weight;
}

