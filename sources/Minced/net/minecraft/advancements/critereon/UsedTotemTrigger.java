// 
// Decompiled by Procyon v0.5.36
// 

package net.minecraft.advancements.critereon;

import java.util.Iterator;
import java.util.List;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.util.Set;
import net.minecraft.advancements.ICriterionInstance;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.EntityPlayerMP;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.common.collect.Maps;
import net.minecraft.advancements.PlayerAdvancements;
import java.util.Map;
import net.minecraft.util.ResourceLocation;
import net.minecraft.advancements.ICriterionTrigger;

public class UsedTotemTrigger implements ICriterionTrigger<Instance>
{
    private static final ResourceLocation ID;
    private final Map<PlayerAdvancements, Listeners> listeners;
    
    public UsedTotemTrigger() {
        this.listeners = (Map<PlayerAdvancements, Listeners>)Maps.newHashMap();
    }
    
    @Override
    public ResourceLocation getId() {
        return UsedTotemTrigger.ID;
    }
    
    @Override
    public void addListener(final PlayerAdvancements playerAdvancementsIn, final Listener<Instance> listener) {
        Listeners usedtotemtrigger$listeners = this.listeners.get(playerAdvancementsIn);
        if (usedtotemtrigger$listeners == null) {
            usedtotemtrigger$listeners = new Listeners(playerAdvancementsIn);
            this.listeners.put(playerAdvancementsIn, usedtotemtrigger$listeners);
        }
        usedtotemtrigger$listeners.add(listener);
    }
    
    @Override
    public void removeListener(final PlayerAdvancements playerAdvancementsIn, final Listener<Instance> listener) {
        final Listeners usedtotemtrigger$listeners = this.listeners.get(playerAdvancementsIn);
        if (usedtotemtrigger$listeners != null) {
            usedtotemtrigger$listeners.remove(listener);
            if (usedtotemtrigger$listeners.isEmpty()) {
                this.listeners.remove(playerAdvancementsIn);
            }
        }
    }
    
    @Override
    public void removeAllListeners(final PlayerAdvancements playerAdvancementsIn) {
        this.listeners.remove(playerAdvancementsIn);
    }
    
    @Override
    public Instance deserializeInstance(final JsonObject json, final JsonDeserializationContext context) {
        final ItemPredicate itempredicate = ItemPredicate.deserialize(json.get("item"));
        return new Instance(itempredicate);
    }
    
    public void trigger(final EntityPlayerMP player, final ItemStack item) {
        final Listeners usedtotemtrigger$listeners = this.listeners.get(player.getAdvancements());
        if (usedtotemtrigger$listeners != null) {
            usedtotemtrigger$listeners.trigger(item);
        }
    }
    
    static {
        ID = new ResourceLocation("used_totem");
    }
    
    public static class Instance extends AbstractCriterionInstance
    {
        private final ItemPredicate item;
        
        public Instance(final ItemPredicate item) {
            super(UsedTotemTrigger.ID);
            this.item = item;
        }
        
        public boolean test(final ItemStack item) {
            return this.item.test(item);
        }
    }
    
    static class Listeners
    {
        private final PlayerAdvancements playerAdvancements;
        private final Set<Listener<Instance>> listeners;
        
        public Listeners(final PlayerAdvancements playerAdvancementsIn) {
            this.listeners = (Set<Listener<Instance>>)Sets.newHashSet();
            this.playerAdvancements = playerAdvancementsIn;
        }
        
        public boolean isEmpty() {
            return this.listeners.isEmpty();
        }
        
        public void add(final Listener<Instance> listener) {
            this.listeners.add(listener);
        }
        
        public void remove(final Listener<Instance> listener) {
            this.listeners.remove(listener);
        }
        
        public void trigger(final ItemStack item) {
            List<Listener<Instance>> list = null;
            for (final Listener<Instance> listener : this.listeners) {
                if (listener.getCriterionInstance().test(item)) {
                    if (list == null) {
                        list = (List<Listener<Instance>>)Lists.newArrayList();
                    }
                    list.add(listener);
                }
            }
            if (list != null) {
                for (final Listener<Instance> listener2 : list) {
                    listener2.grantCriterion(this.playerAdvancements);
                }
            }
        }
    }
}
