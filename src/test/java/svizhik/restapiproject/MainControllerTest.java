package svizhik.restapiproject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import svizhik.restapiproject.controller.MainController;
import svizhik.restapiproject.dto.Cat;
import svizhik.restapiproject.service.CatService;

import java.util.Optional;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class MainControllerTest {

    @Mock
    private CatService catService;

    @InjectMocks
    private MainController mainController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(mainController).build();
    }

    @Test
    void getCat() throws Exception {

        mockMvc.perform(get("/api/MainController/cat"))
                .andExpect(status().isOk());
        verify(catService, times(1))
                .getCat(7);
    }

    @Test
    void addCat() throws Exception {

        Cat newCat = new Cat(17, "Barsik", 3, 5);

        mockMvc.perform(get("/api/MainController/cat"))
                .andExpect(status().isOk());
        verify(catService, times(1))
                .addCat(newCat);
    }

    @Test
    void updateCat() throws Exception {

        Cat newCat = new Cat(17, "Barsik", 4, 5);

        mockMvc.perform(get("/api/MainController/cat"))
                .andExpect(status().isOk());
        verify(catService, times(1))
                .updateCat(Optional.of(newCat));
    }

    @Test
    void deleteCat() throws Exception {

        mockMvc.perform(get("/api/MainController/cat"))
                .andExpect(status().isOk());
        verify(catService, times(1))
                .deleteCat(17);
    }

}

