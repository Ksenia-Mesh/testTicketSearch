package ru.netology.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import ru.netology.data.TicketData;
import ru.netology.exception.AlreadyExistsException;
import ru.netology.exception.NotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class TicketRepositoryTest {
    TicketRepository repositoryEmpty = new TicketRepository();
    TicketRepository repositoryWithOneTicket = new TicketRepository();
    TicketRepository repositoryWithTenTicket = new TicketRepository();

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

    @BeforeEach
    void setup() {
        repositoryWithOneTicket.addTicket(ticket1);
        repositoryWithTenTicket.addTicket(ticket1);
        repositoryWithTenTicket.addTicket(ticket2);
        repositoryWithTenTicket.addTicket(ticket3);
        repositoryWithTenTicket.addTicket(ticket4);
        repositoryWithTenTicket.addTicket(ticket5);
        repositoryWithTenTicket.addTicket(ticket6);
        repositoryWithTenTicket.addTicket(ticket7);
        repositoryWithTenTicket.addTicket(ticket8);
        repositoryWithTenTicket.addTicket(ticket9);
        repositoryWithTenTicket.addTicket(ticket10);
    }

    @Test
    void shouldAddTicket() {
        TicketData[] expected = new TicketData[]{
                ticket1,
                ticket2,
                ticket3};
        repositoryEmpty.addTicket(ticket1);
        repositoryEmpty.addTicket(ticket2);
        repositoryEmpty.addTicket(ticket3);
        assertArrayEquals(expected, repositoryEmpty.findAll());
    }

    @Test
    void shouldAddTicketException() {
        assertThrows(AlreadyExistsException.class, () -> {
            repositoryWithTenTicket.addTicket(ticket1);
        });
    }

    @Test
    void shouldRemoveById() {
        TicketData[] expected = new TicketData[]{
                ticket1,
                ticket2,
                ticket3,
                ticket4,
                ticket5,
                ticket9,
                ticket10};
        repositoryWithTenTicket.removeById(6);
        repositoryWithTenTicket.removeById(7);
        repositoryWithTenTicket.removeById(8);
        assertArrayEquals(expected, repositoryWithTenTicket.findAll());
    }

    @Test
    void shouldRemoveByIdException() {
        assertThrows(NotFoundException.class, () -> {
            repositoryWithTenTicket.removeById(11);
        });
    }

    @Test
    void shouldFindByIdPass() {
        assertEquals(ticket8, repositoryWithTenTicket.findById(8));
    }

    @Test
    void shouldFindByIdNull() {
        assertNull(repositoryWithTenTicket.findById(12));
    }
}
