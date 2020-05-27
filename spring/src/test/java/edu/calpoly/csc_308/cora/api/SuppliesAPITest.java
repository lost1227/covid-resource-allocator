package edu.calpoly.csc_308.cora.api;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;

import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.mockito.Mockito;

import edu.calpoly.csc_308.cora.api.request.SuppliesFilterRequestModel;
import edu.calpoly.csc_308.cora.api.response.SuppliesResponse;
import edu.calpoly.csc_308.cora.data.supplies.SupplyDAO;
import edu.calpoly.csc_308.cora.data.supplies.SupplyRepository;
import edu.calpoly.csc_308.cora.data.supplies.SupplyType;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class SuppliesAPITest {

    @LocalServerPort
    private int port;

    @Autowired
    private SuppliesAPI api;

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private SupplyRepository messengerService;

    @BeforeEach
    public void setup() {
        SupplyDAO mockSupply1 = new SupplyDAO(
            "KN-95 Masks",
            "Tempe, AZ",
            1,
            "In need of extra KN-95 medical grade masks.",
            5L,
            SupplyType.REQUEST,
            0
        );
        Mockito.doReturn(Arrays.asList(mockSupply1)).when(messengerService).findAll();
    }

    @Test
    public void testContextLoads() throws Exception {
        assertThat(api).isNotNull();
    }

    @Test
    public void testGetSupplies() {
        SuppliesFilterRequestModel request = new SuppliesFilterRequestModel();
        request.enabledFilters = new String[]{};

        SuppliesResponse response = this.restTemplate.postForObject(
            "http://localhost:"+this.port+"/api/supplies",
            request,
            SuppliesResponse.class
        );

        assertThat(response).isNotNull();
        assertThat(response.ok).isTrue();
    }
    
}
