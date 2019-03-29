/*******************************************************************************
 * Copyright 2019 grondag
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy
 * of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/

package grondag.frex.mixin;

import java.util.Random;
import java.util.function.Supplier;

import org.spongepowered.asm.mixin.Mixin;

import grondag.frex.api.core.FabricBakedModel;
import grondag.frex.api.core.RenderContext;
import grondag.frex.api.core.TerrainBlockView;
import net.minecraft.block.BlockState;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;

/**
 * Avoids instanceof checks and enables consistent code path for all baked models.
 */
@Mixin(BakedModel.class)
public interface MixinBakedModel extends FabricBakedModel {
    @Override
    public default boolean isVanillaAdapter() {
        return true;
    }
    
    @Override
    public default void emitBlockQuads(TerrainBlockView blockView, BlockState state, BlockPos pos, Supplier<Random> randomSupplier, RenderContext context) {
        context.fallbackConsumer().accept((BakedModel)this);
    }
    
    @Override
    default void emitItemQuads(ItemStack stack, Supplier<Random> randomSupplier, RenderContext context) {
        context.fallbackConsumer().accept((BakedModel)this);        
    }
}