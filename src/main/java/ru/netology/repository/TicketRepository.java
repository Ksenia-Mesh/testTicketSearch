package ru.netology.repository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.netology.data.TicketData;
import ru.netology.exception.AlreadyExistsException;
import ru.netology.exception.NotFoundException;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TicketRepository {
    private TicketData[] tickets = new TicketData[0];

    public TicketData[] findAll() {
        return getTickets();
    }

    public void addTicket(TicketData addTicket) {
        if (findById(addTicket.getId()) != null) {
            throw new AlreadyExistsException("Билет с ID: " + addTicket.getId() + " уже добавлен в репозиторий!!!");
        }
        TicketData[] tmp = new TicketData[tickets.length + 1];
        System.arraycopy(tickets, 0, tmp, 0, tickets.length);
        tmp[tickets.length] = addTicket;
        tickets = tmp;
    }

    public void removeById(int removeId) {
        if (findById(removeId) == null) {
            throw new NotFoundException("Билет с ID: " + removeId + " не найден!!!");
        }
        TicketData[] tmp = new TicketData[tickets.length - 1];
        for (int i = 0, j = 0; j < tickets.length; i++, j++) {
            if (tickets[j].getId() == removeId) {
                j++;
            }
            tmp[i] = tickets[j];
        }
        tickets = tmp;
    }

    public TicketData findById(int findId) {
        for (TicketData ticket : tickets) {
            if (ticket.getId() == findId) {
                return ticket;
            }
        }
        return null;
    }
}