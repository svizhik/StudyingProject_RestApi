package svizhik.restapiproject.unit;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import svizhik.restapiproject.dto.Cat;
import svizhik.restapiproject.service.CatService;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class MainControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CatService catService;

    @Test
    public void testGetCat() throws Exception {
        Cat cat = new Cat(1, "Tom", 7, 10); // Пример кота
        when(catService.getCat(anyInt())).thenReturn(List.of(cat));

        MvcResult result = mockMvc.perform(get("/api/cat/get/age?age=7")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        ObjectMapper objectMapper = new ObjectMapper();
        Cat[] returnedCats = objectMapper.readValue(content, Cat[].class);

        assertEquals("", 1, returnedCats.length);
        assertEquals("", cat, returnedCats[0]);

        System.out.println("Response content: " + content);
        System.out.println("Number of returned cats: " + returnedCats.length);
    }

    @Test
    public void testAddCat() throws Exception {
        Cat newCat = new Cat(7, "Tom", 3, 5);

        when(catService.addCat(any(Cat.class))).thenReturn(true);

        ObjectMapper objectMapper = new ObjectMapper();
        String catJson = objectMapper.writeValueAsString(newCat);

        MvcResult result = mockMvc.perform(post("/api/cat/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(catJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Cat added successfully"))
                .andReturn();

        String content = result.getResponse().getContentAsString();

        System.out.println("Response content: " + content);
    }

    @Test
    public void testUpdateCat() throws Exception {
        Cat updatedCat = new Cat(1, "Tom", 4, 6);

        when(catService.updateCat(any(Optional.class))).thenReturn(true);

        ObjectMapper objectMapper = new ObjectMapper();
        String catJson = objectMapper.writeValueAsString(updatedCat);

        MvcResult result = mockMvc.perform(put("/api/cat/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(catJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Cat updated successfully"))
                .andReturn();

        String content = result.getResponse().getContentAsString();

        System.out.println("Response content: " + content);
    }

    @Test
    public void testDeleteCat() throws Exception {
        when(catService.deleteCat(eq(1))).thenReturn(true);

        MvcResult resultSuccessfulDeletion = mockMvc.perform(delete("/api/cat/delete?id=1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("The size of List<Cat> was changed."))
                .andReturn();
        String contentSuccessful = resultSuccessfulDeletion.getResponse().getContentAsString();
        System.out.println("Successful deletion response content: " + contentSuccessful);

        // не существующий
        when(catService.deleteCat(eq(99))).thenReturn(false);

        MvcResult resultUnsuccessfulDeletion = mockMvc.perform(delete("/api/cat/delete?id=99"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("There is no Cat with such ID."))
                .andReturn();
        String contentUnsuccessful = resultUnsuccessfulDeletion.getResponse().getContentAsString();
        System.out.println("Unsuccessful deletion response content: " + contentUnsuccessful);
    }
}