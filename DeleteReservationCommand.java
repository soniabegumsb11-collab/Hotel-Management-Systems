import java.util.ArrayList;

public class DeleteReservationCommand implements Command {
    private HotelDataState state;
    private int index;

    public DeleteReservationCommand(HotelDataState state, int index) {
        this.state = state;
        this.index = index;
    }

    @Override
    public void execute() {
        ArrayList<Reservation> reservations = state.getReservations();
        if(index >= 0 && index < reservations.size()) {
            reservations.remove(index);
            state.setReservations(reservations);
        }
    }
}
