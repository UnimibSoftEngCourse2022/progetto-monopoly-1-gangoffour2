import React, {RefObject} from 'react';
import style from './popup.module.css'

interface Props {
    trigger: boolean,
    onClose: Function,
    title: string
}

interface State {
    hide_trigger: boolean,
    trigger: boolean,
}

class Popup extends React.Component<Props, State> {
    private wrapperRef: RefObject<HTMLDivElement>;

    constructor(props: any) {
        super(props);

        this.state = {
            trigger: this.props.trigger,
            hide_trigger: false
        }

        this.wrapperRef = React.createRef()
        this.handleClickOutside = this.handleClickOutside.bind(this);
    }

    onClose() {
        this.startAnimationClosePopup();
        this.props.onClose();
    }

    startAnimationClosePopup() {
        this.setState({ hide_trigger: true });
    }

    closePopupAfterAnimation(name_animation: string) {

        if (name_animation == "hide-animation") {
            this.setState({ hide_trigger: false });
            this.onClose();
        }
    }

    handleClickOutside(event: any) {
        if (this.props.trigger && this.wrapperRef &&
            this.wrapperRef.current !== null && !this.wrapperRef.current.contains(event.target)
        ) {
            this.onClose();
        }
    }

    componentDidMount() {
        document.addEventListener('mousedown', this.handleClickOutside);
    }

    componentWillUnmount() {
        document.removeEventListener('mousedown', this.handleClickOutside);
    }

    render() {
        let hide = this.state?.hide_trigger === true ? " hide" : "";

        return (this.props.trigger === true) ? (
            <div className={style.popup_container + hide} onAnimationEnd={(evt) => this.closePopupAfterAnimation(evt.nativeEvent.animationName)}>
                <div ref={this.wrapperRef} className={style.popup}>
                    <div className={style.popup_title}>{this.props.title}</div>
                    <div className={style.popup_content}>
                        {this.props.children}
                    </div>
                </div>
            </div>
        ) : "";
    }
}


export default Popup;
