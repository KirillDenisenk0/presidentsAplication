package lesson13_2;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

public class President implements Serializable {
    private String nameOfPresident;
    private LocalDate beginningOfReign;
    private LocalDate   endOfReign;
    private String consignment;

    public President(String nameOfPresident, LocalDate beginningOfReign, LocalDate  endOfReign, String consignment) {
        this.nameOfPresident = nameOfPresident;
        this.beginningOfReign = beginningOfReign;
        this.endOfReign = endOfReign;
        this.consignment = consignment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        President president = (President) o;
        return Objects.equals(nameOfPresident, president.nameOfPresident);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameOfPresident);
    }

    @Override
    public String toString() {
        return nameOfPresident + ": " + beginningOfReign + " - " + endOfReign + "; " + consignment + "\n";
    }

    public String getNameOfPresident() {
        return nameOfPresident;
    }

    public LocalDate getBeginningOfReign() {
        return beginningOfReign;
    }

    public LocalDate getEndOfReign() {
        return endOfReign;
    }

    public String getConsignment() {
        return consignment;
    }
}
