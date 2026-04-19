public class Main {
    public static void main(String[] args)
     {

        HotelFacade hotelFacade = new HotelFacade();

        hotelFacade.addCustomer();
        hotelFacade.addRoom();
        hotelFacade.makeReservation();
    }
}
