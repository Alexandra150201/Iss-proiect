package theater.persistence;

import theater.model.Booking;

public interface IBookingRepo extends  Repository<Integer, Booking>{
    public Integer countEmptySeatsSituation();
}
