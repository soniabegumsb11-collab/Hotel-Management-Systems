import java.util.List;

public class HotelCollection<T> implements Aggregate<T> {
    private List<T> items;

    public HotelCollection(List<T> items) {
        this.items = items;
    }

    @Override
    public Iterator<T> createIterator() {
        return new HotelIterator<>(items);
    }
}
