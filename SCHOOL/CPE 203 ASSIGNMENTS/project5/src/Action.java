/*
Action: ideally what our various entities might do in our virutal world
 */

public abstract class Action
{
   private Entity entity;

   public Action(Entity entity)
   {
      this.entity = entity;
   }

   protected Entity getEntity() {
      return entity;
   }

   protected abstract void executeAction(EventScheduler scheduler);
}