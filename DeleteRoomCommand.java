import java.util.ArrayList;

public class DeleteRoomCommand implements Command {
    private HotelDataState state;
    private int index;

    public DeleteRoomCommand(HotelDataState state, int index) {
        this.state = state;
        this.index = index;
    }

    @Override
    public void execute() {
        ArrayList<Room> rooms = state.getRooms();
        if(index >= 0 && index < rooms.size()) {
            rooms.remove(index);
            state.setRooms(rooms);
        }
    }
}
