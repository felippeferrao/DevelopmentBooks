import PropTypes from 'prop-types';
import { useEffect } from 'react';
import { useLocation } from 'react-router-dom';

// ==============================|| NAVIGATION - SCROLL TO TOP ||============================== //

const ScrollTop = ({ children }) => {
    const location = useLocation();
    const { pathname } = location;

    useEffect(() => {
        window.scrollTo({
            top: 0,
            left: 0,
            behavior: 'smooth'
        });
    }, [pathname]);
    //Sempre que o PAthname Muda, ele identifica e posiciona a aplicação para cima

    return children || null;
};

ScrollTop.propTypes = {
    children: PropTypes.node
};

export default ScrollTop;

/*
Este é um componente React que usa o hook useEffect para rolar a página para o topo sempre que a localização (URL) do aplicativo mudar. O componente ScrollTop é exportado como um módulo padrão e pode ser usado em outros componentes React.
O componente usa o hook useLocation do react-router-dom para obter informações sobre a localização atual do aplicativo. 
Ele também recebe uma propriedade children, que é usada para renderizar o conteúdo que está sendo "envelopado" pelo componente ScrollTop.
A função useEffect é executada sempre que a localização (pathname) muda. 
Dentro da função useEffect, a janela é rolada para o topo usando a função scrollTo do objeto window. 
A opção behavior é definida como 'smooth' para criar um efeito de rolagem suave.
Por fim, o componente ScrollTop retorna o children passado para ele ou null se nenhum children for passado.
O PropTypes é usado para garantir que a propriedade children seja um nó válido.

O componente ScrollTop usa o hook useLocation do react-router-dom para obter informações sobre a localização atual do aplicativo. Esse hook retorna um objeto com informações sobre a localização atual do aplicativo, incluindo o pathname.
O pathname é uma propriedade do objeto retornado pelo useLocation que contém o caminho atual da URL. O useEffect é configurado para observar mudanças no pathname, ou seja, sempre que houver uma alteração no caminho da URL, a função useEffect será executada novamente e a janela será rolada para o topo.
Isso permite que o componente ScrollTop saiba quando a localização mudou e execute a rolagem para o topo correspondente.
*/
