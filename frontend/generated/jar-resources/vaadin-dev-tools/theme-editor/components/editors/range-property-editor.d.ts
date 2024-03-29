import {PropertyValues, TemplateResult} from 'lit';
import {BasePropertyEditor} from './base-property-editor';

export declare class RangePropertyEditor extends BasePropertyEditor {
    private updateSliderValue;
    private selectedPresetIndex;
    private presets;
    protected update(changedProperties: PropertyValues): void;

    static get styles(): import("lit").CSSResultGroup[];
    private handleSliderInput;
    private handleSliderChange;
    private handleValueChange;
    protected dispatchChange(value: string): void;
    protected updateValueFromTheme(): void;

    protected renderEditor(): TemplateResult;
}
