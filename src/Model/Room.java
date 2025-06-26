package Model;

import java.text.SimpleDateFormat;

public class Room {
    private int id;
    private RoomType type;
    private int price;

    public Room(int id, RoomType type, int price) {
        this.id = id;
        this.type = type;
        this.price = price;
    }

    public Room() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RoomType getType() {
        return type;
    }

    public void setType(RoomType type) {
        this.type = type;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format(
                "🏨 Room Details\n" +
                        "--------------------------\n" +
                        "🔢 Room Number  : %d\n" +
                        "💰 Price/Night  : %d\n" +
                        "🏷️ Room Type    : %s\n",
                id,
                price,
                type.toString()
        );
    }

}
