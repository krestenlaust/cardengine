package cardengine;

/**
 * A place where cards can be stored
 */
public interface CardField {

    void PutCard(Card card);

    Card GetCard();
}
