package models;
public class BookingResponse {
    private int bookingid;
    private Booker booking;

    public BookingResponse(int bookingid, Booker booking) {
        this.bookingid = bookingid;
        this.booking = booking;
    }

    public BookingResponse() {
    }


    public int getBookingid() {
        return bookingid;
    }

    public void setBookingid(int bookingid) {
        this.bookingid = bookingid;
    }

    public Booker getBooking() {
        return booking;
    }

    public void setBooking(Booker booking) {
        this.booking = booking;
    }
}