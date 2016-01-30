/*
 * Copyright 2013 serso aka se.solovyev
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Contact details
 *
 * Email: se.solovyev@gmail.com
 * Site:  http://se.solovyev.org
 */

package org.solovyev.android.calculator.variables;

import android.os.Bundle;
import android.os.Parcelable;
import org.solovyev.android.calculator.*;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class VariablesActivity extends BaseActivity implements CalculatorEventListener {

    public static final String EXTRA_VARIABLE = "variable";

    public VariablesActivity() {
        super(R.layout.main_empty, VariablesActivity.class.getSimpleName());
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final Bundle extras = getIntent().getExtras();
        final Parcelable variable = extras != null ? extras.getParcelable(EXTRA_VARIABLE) : null;

        for (VariableCategory category : VariableCategory.values()) {
            final Bundle arguments = new Bundle(2);
            if (category == VariableCategory.my && variable != null) {
                arguments.putParcelable(VariablesFragment.ARG_VARIABLE, variable);
            }
            arguments.putString(VariablesFragment.ARG_CATEGORY, category.name());
            ui.addTab(this, CalculatorFragmentType.variables.createSubFragmentTag(category.name()), CalculatorFragmentType.variables.getFragmentClass(), arguments, category.title(), R.id.main_layout);
        }
    }

    @Override
    public void onCalculatorEvent(@Nonnull CalculatorEventData calculatorEventData, @Nonnull CalculatorEventType calculatorEventType, @Nullable Object data) {
        switch (calculatorEventType) {
            case use_constant:
                this.finish();
                break;
        }
    }
}