import {
    errorMessageProperties as o,
    helperTextProperties as i,
    inputFieldProperties as t,
    labelProperties as a
} from "./vaadin-text-field-GsooWDDF.js";
import {L as e} from "./copilot-5xZabcKF.js";
import {standardButtonProperties as p} from "./vaadin-button-ghqKrfmI.js";

const d = {
  tagName: "vaadin-number-field",
  displayName: "Number Field",
  elements: [
    {
      selector: "vaadin-number-field::part(input-field)",
      displayName: "Input field",
      properties: t
    },
    {
      selector: "vaadin-number-field::part(label)",
      displayName: "Label",
      properties: a
    },
    {
      selector: "vaadin-number-field::part(helper-text)",
      displayName: "Helper text",
      properties: i
    },
    {
      selector: "vaadin-number-field::part(error-message)",
      displayName: "Error message",
      properties: o
    },
    {
      selector: "vaadin-number-field::part(clear-button)",
      displayName: "Clear button",
      properties: p
    },
    {
      selector: "vaadin-number-field::part(increase-button)",
      displayName: "Increase button",
      properties: [e.iconColor, e.iconSize]
    },
    {
      selector: "vaadin-number-field::part(decrease-button)",
      displayName: "Decrease button",
      properties: [e.iconColor, e.iconSize]
    }
  ],
  setupElement(r) {
    r.stepButtonsVisible = !0;
  }
};
export {
  d as default
};
