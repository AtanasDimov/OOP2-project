package DTOLibraryItems;

import Contracts.BorrowItemInterface;

import java.time.LocalDateTime;
import java.util.UUID;

public class BorrowItem implements BorrowItemInterface {
    private UUID itemID;
    private UUID readerID;
    private LocalDateTime lendingHour;
    private LocalDateTime dueBy;
    private boolean isArchive = false;

    public BorrowItem(UUID itemID, UUID readerID, LocalDateTime lendingHour, LocalDateTime dueBy) {
        this.itemID = itemID;
        this.readerID = readerID;
        this.lendingHour = lendingHour;
        this.dueBy = dueBy;
    }

    public UUID getItemID() {
        return itemID;
    }

    public void setItemID(UUID itemID) {
        this.itemID = itemID;
    }

    public UUID getReaderID() {
        return readerID;
    }

    public void setReaderID(UUID readerID) {
        this.readerID = readerID;
    }

    public LocalDateTime getLendingHour() {
        return lendingHour;
    }

    public void setLendingHour(LocalDateTime lendingHour) {
        this.lendingHour = lendingHour;
    }

    public LocalDateTime getDueBy() {
        return dueBy;
    }

    public void setDueBy(LocalDateTime dueBy) {
        this.dueBy = dueBy;
    }

    public boolean isArchive() {
        return this.isArchive;
    }

    public void ArchiveItem(){
        this.isArchive = true;
    }
}
