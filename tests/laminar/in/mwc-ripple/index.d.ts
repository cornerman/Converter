import { RippleBase } from './mwc-ripple-base';
declare global {
    interface HTMLElementTagNameMap {
        'mwc-ripple': Ripple;
    }
}

/** @soyCompatible */
export declare class Ripple extends RippleBase {
    static styles: import("lit-element").CSSResult;
}
