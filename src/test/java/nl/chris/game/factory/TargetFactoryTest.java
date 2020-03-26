package nl.chris.game.factory;

import org.junit.jupiter.api.Assertions;


class TargetFactoryTest {

    public void testFactory() {
        TargetFactory targetFactory = new TargetFactory();
        Assertions.assertNotEquals(targetFactory.newTarget(), targetFactory.newTarget());
    }
}
