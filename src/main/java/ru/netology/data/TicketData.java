package ru.netology.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TicketData implements Comparable<TicketData> {
    private int id;
    private int price;
    private String from;
    private String to;
    private int duration;

    @Override
    public int compareTo(@NotNull TicketData o) {
        return price - o.getPrice();
    }
}