import {
    inputFieldProperties as e,
    labelProperties as a,
    helperTextProperties as r,
    errorMessageProperties as t
} from "./vaadin-text-field-GsooWDDF.js";
import {standardButtonProperties as p} from "./vaadin-button-ghqKrfmI.js";
import "./copilot-5xZabcKF.js";

const l = {
    tagName: "vaadin-text-area",
    displayName: "Text Area",
    elements: [
        {
            selector: "vaadin-text-area::part(input-field)",
            displayName: "Input field",
            properties: e
        },
        {
            selector: "vaadin-text-area::part(label)",
            displayName: "Label",
            properties: a
        },
        {
            selector: "vaadin-text-area::part(helper-text)",
            displayName: "Helper text",
            properties: r
        },
        {
            selector: "vaadin-text-area::part(error-message)",
            displayName: "Error message",
            properties: t
        },
        {
            selector: "vaadin-text-area::part(clear-button)",
            displayName: "Clear button",
            properties: p
        }
    ]
};
export {
    l as default
};
