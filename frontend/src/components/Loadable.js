import { Suspense } from 'react';

// project import
import Loader from './Loader';

// ==============================|| LOADABLE - LAZY LOADING ||============================== //

const Loadable = (Component) => (props) =>
    (
        <Suspense fallback={<Loader />}>
            <Component {...props} />
        </Suspense>
    );

export default Loadable;

/*
Este código exporta uma função de ordem superior chamada Loadable, que envolve um componente em um componente Suspense. 
A função Loadable é usada para permitir o carregamento atrasado de um componente e renderizar um indicador de carregamento enquanto o componente está sendo carregado.
A função Loadable recebe um componente como argumento e retorna uma função que retorna o mesmo componente envolvido em um Suspense.
O componente passado para Loadable é então carregado de forma assíncrona.
O componente Suspense é usado para suspender a renderização de um componente enquanto ele está sendo carregado. 
Durante esse período, um fallback pode ser renderizado, que é o que acontece aqui com o componente Loader.
O componente Loader é um componente de indicação de carregamento que é exibido enquanto o componente é carregado de forma assíncrona.

Este padrão de carregamento atrasado é conhecido como carregamento preguiçoso ou lazy loading, e é usado para carregar componentes grandes ou complexos apenas quando necessário, em vez de carregá-los todos de uma só vez.
Com o Loadable, é possível carregar um componente de forma assíncrona e renderizar um indicador de carregamento para que o usuário saiba que algo está acontecendo enquanto o componente está sendo carregado. Quando o componente estiver pronto para ser renderizado, ele será renderizado automaticamente em vez do indicador de carregamento.
*/
