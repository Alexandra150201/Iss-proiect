package theater.service;

import theater.model.*;
import theater.persistence.BookingRepository;
import theater.persistence.SeatRepository;
import theater.persistence.ShowEventRepository;
import theater.persistence.TheaterParticipantRepository;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

public class Service {
    private BookingRepository bookingRepository;
    private SeatRepository seatRepository;
    private ShowEventRepository showEventRepository;
    private TheaterParticipantRepository theaterParticipantRepository;

    public Service(BookingRepository bookingRepository, SeatRepository seatRepository, ShowEventRepository showEventRepository, TheaterParticipantRepository theaterParticipantRepository) {
        this.bookingRepository = bookingRepository;
        this.seatRepository = seatRepository;
        this.showEventRepository = showEventRepository;
        this.theaterParticipantRepository = theaterParticipantRepository;
    }

    public TheaterParticipant findParticipant(String username,String type) {
        Iterable<TheaterParticipant> theaterParticipants= theaterParticipantRepository.findAll();
        for (TheaterParticipant a : theaterParticipants) {
            if(a.getUsername().equals(username) && a.getType()==AccountType.valueOf(type))
                return a;
        }
        return null;
    }

    public ShowEvent getShowEvent() {
        if(StreamSupport.stream(showEventRepository.findAll().spliterator(), false).toList().size()!=0)
            return StreamSupport.stream(showEventRepository.findAll().spliterator(), false).toList().get(0);
        else return null;
    }

    public void makeBooking(String name, Double telephone, Iterable<Seat> seats, ShowEvent showEvent) {
        Booking booking = new Booking(name,telephone,showEvent);
        bookingRepository.save(booking);
        for(Seat s : seats){
            s.setStatus(StatusType.taken);
            s.setBooking(booking);
            seatRepository.update(s);
        }
    }

    public Iterable<Seat> getSeats(ShowEvent showEvent) {
        List<Seat> seatsShow = new ArrayList<>();
        Iterable<Seat> seats=seatRepository.findAll();
        int number=getShowEvent().getNumberOfSeats();
        int i =0;
        for(Seat s : seats)
        {
            if(i<number)
            {
                seatsShow.add(s);
                i++;
            }
        }
        return seatsShow;
    }

    public Seat getSeatFromPosition(String position) {
        Iterable<Seat> seats= seatRepository.findAll();
        for(Seat s : seats)
            if(s.getNumber().equals(Integer.valueOf(position)))
                return  s;
        return null;
    }

    public Iterable<Booking> getBookings(ShowEvent show) {
        Iterable<Booking> bookings= bookingRepository.findAll();
        ArrayList<Booking> bb= new ArrayList<>();
        for(Booking b : bookings)
            if(b.getShowEvent().getName().equals(show.getName()))
                bb.add(b);
        return bb;
    }

    public ShowEvent addEvent(String nume, int seats) {
        ShowEvent event= new ShowEvent(1,nume,seats);
        showEventRepository.save(event);
        ShowEvent ev=getShowEvent();
        return ev;
    }

    public ShowEvent deleteEvent(String nume, Integer seats) {
        ShowEvent event= new ShowEvent(1,nume,seats);
        try {
            Iterable<Seat> seatss=getSeats(event);
            Iterable<Booking> bookings=getBookings(event);
            for( Seat seat :seatss )
                seatRepository.update(new Seat(seat.getId(),seat.getPosition(),seat.getNumber(),seat.getPrice(),StatusType.empty,null));
            for( Booking b : bookings )
                bookingRepository.delete(b);
            showEventRepository.delete(event);
            return  getShowEvent();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return getShowEvent();
    }


    public ShowEvent updateEvent(String nume, int seats) {
        ShowEvent event= new ShowEvent(1,nume,seats);
        showEventRepository.update(event);
        ShowEvent ev=getShowEvent();
        return ev;
    }
}
