public class Animation extends Action{
    private int repeatCount;

    public Animation(Entity entity, int repeatCount)
    {
        super(entity);
        this.repeatCount = repeatCount;
    }

    public void executeAction(EventScheduler scheduler)
    {
        ((AnimatedEntity)this.getEntity()).nextImage();

        if (this.repeatCount != 1)
        {
            scheduler.scheduleEvent(this.getEntity(),
                    Animation.createAnimationAction(Math.max(this.repeatCount - 1, 0), this.getEntity()),
                    ((AnimatedEntity)getEntity()).getAnimationPeriod());
        }
    }

    public static Action createAnimationAction(int repeatCount, Entity entity)
    {
        return new Animation(entity, repeatCount);
    }

}
