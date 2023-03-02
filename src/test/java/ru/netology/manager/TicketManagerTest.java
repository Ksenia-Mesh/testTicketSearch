package ru.netology.manager;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import ru.netology.data.TicketData;
import ru.netology.exception.NotFoundException;
import ru.netology.repository.TicketRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;

class TicketManagerTest {
    @Mock
    TicketRepository repository = Mockito.mock(TicketRepository.class);
    @InjectMocks
    TicketManager manager = new TicketManager(repository);

    // тестовые данные
    TicketData ticket1 = new TicketData(1, 50_000, "DME", "LED", 60);
    TicketData ticket2 = new TicketData(2, 25_000, "SVO", "LED", 100);
    TicketData ticket3 = new TicketData(3, 15_000, "VKO", "RVH", 95);
    TicketData ticket4 = new TicketData(4, 8_000, "SVO", "LED", 80);
    TicketData ticket5 = new TicketData(5, 12_000, "SVO", "LED", 85);
    TicketData ticket6 = new TicketData(6, 14_000, "VKO", "LED", 75);
    TicketData ticket7 = new TicketData(7, 10_680, "SVO", "RVH", 80);
    TicketData ticket8 = new TicketData(8, 15_950, "SVO", "LED", 85);
    TicketData ticket9 = new TicketData(9, 12_760, "VKO", "LED", 70);
    TicketData ticket10 = new TicketData(10, 10_680, "SVO", "RVH", 80);

    TicketData[] mockEmpty = new TicketData[0];
    TicketData[] mockOneTicket = new TicketData[]{ticket1};
    TicketData[] mockTenTicket = new TicketData[]{
            ticket1,
            ticket2,
            ticket3,
            ticket4,
            ticket5,
            ticket6,
            ticket7,
            ticket8,
            ticket9,
            ticket10};


    @Test
    void shouldNotFindMockEmpty() {
        doReturn(mockEmpty).when(repository).findAll();
        assertThrows(NotFoundException.class, () -> {
            manager.findAll("DME", "LED");
        });
    }

    @Test
    void shouldNotFindMockWithOneTicket() {
        doReturn(mockOneTicket).when(repository).findAll();
        assertThrows(NotFoundException.class, () -> {
            manager.findAll("DME", "PES");
        });
    }

    @Test
    void shouldNotFindMockWithTenTicket() {
        doReturn(mockTenTicket).when(repository).findAll();
        assertThrows(NotFoundException.class, () -> {
            manager.findAll("VKO", "PES");
        });
    }
    @Test
    void shouldFindOneResultMockWithOneTicket() {
        doReturn(mockOneTicket).when(repository).findAll();
        TicketData[] expected = new TicketData[]{ticket1};
        assertArrayEquals(expected, manager.findAll("dme", "led"));
    }

    @Test
    void shouldFindOneResultMockWithTenTicket() {
        doReturn(mockTenTicket).when(repository).findAll();
        TicketData[] expected = new TicketData[]{ticket3};
        assertArrayEquals(expected, manager.findAll("vko", "rvh"));
    }

    @Test
    void shouldFindManyResultsMockWithTenTicketOne() {
        doReturn(mockTenTicket).when(repository).findAll();
        TicketData[] expected = new TicketData[]{
                ticket7,
                ticket10};
        assertArrayEquals(expected, manager.findAll("svo", "rvh"));
    }

    @Test
    void shouldMatchesFromToTrue() {
        assertTrue(manager.matchesFromTo(ticket1, "dme", "led"));
    }

    @Test
    void shouldMatchesFromToFalseOne() {
        assertFalse(manager.matchesFromTo(ticket1, "SVO", "LED"));
    }

    @Test
    void shouldMatchesFromToFalseTwo() {
        assertFalse(manager.matchesFromTo(ticket1, "DME", "RVH"));
    }

    @Test
    void shouldMatchesFromToFalseThree() {
        assertFalse(manager.matchesFromTo(ticket1, "VKO", "RVH"));
    }
}