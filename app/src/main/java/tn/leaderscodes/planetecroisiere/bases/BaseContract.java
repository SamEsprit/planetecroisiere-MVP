/*
 * Copyright (C) 2017 Leonid Ustenko Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package tn.leaderscodes.planetecroisiere.bases;

/**
 * Base contract of a View and Presenter
 *
 * @author Leonid Ustenko (Leo.Droidcoder@gmail.com)
 * @since 0.1.0
 */
public interface BaseContract {

    /**
     * Base View contract
     *
     * @author Leonid Ustenko (Leo.Droidcoder@gmail.com)
     * @since 0.1.0
     */
    interface View {

        /**
         * Show/hide progress view
         *
         * @param show pass in `true` to show progress and `false` to hide
         *
         * @author Leonid Ustenko (Leo.Droidcoder@gmail.com)
         * @since 0.1.0
         */
        void showProgress(boolean show);
    }

    /**
     * Base Presenter contract
     *
     * @author Leonid Ustenko (Leo.Droidcoder@gmail.com)
     * @since 0.1.0
     */
    interface Presenter {

        /**
         * Called when Presenter is Attached to a View
         *
         * @author Leonid Ustenko (Leo.Droidcoder@gmail.com)
         * @since 0.1.0
         */
        void onAttach();

        /**
         * Called when Presenter is detached from a View.
         *
         * @author Leonid Ustenko (Leo.Droidcoder@gmail.com)
         * @since 0.1.0
         */
        void onDetach();
    }
}
